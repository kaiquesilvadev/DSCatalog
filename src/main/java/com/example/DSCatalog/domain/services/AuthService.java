package com.example.DSCatalog.domain.services;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DSCatalog.domain.dto.request.EmailRequest;
import com.example.DSCatalog.domain.dto.request.NovaSenhaRequest;
import com.example.DSCatalog.domain.entities.PasswordRecover;
import com.example.DSCatalog.domain.entities.User;
import com.example.DSCatalog.domain.exception.TokenInvalidoException;
import com.example.DSCatalog.domain.exception.UserNaoEncontradoException;
import com.example.DSCatalog.domain.repositories.PasswordRecoverRepository;
import com.example.DSCatalog.domain.repositories.UserRepository;

@Service
public class AuthService {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom secureRandom = new SecureRandom();

	@Value("${email.password-recover.token.minutes}")
	private Long tokenMinutos;
	
	@Value("${email.password-recover.uri}")
	private String recoverUrl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordRecoverRepository recoverRepository;
	
	@Autowired
	private EmailService emailService;

	@Transactional(propagation = Propagation.SUPPORTS)
	public void criaToken(EmailRequest emailRequest) {
		User user = userRepository.findByEmail(emailRequest.getEmail());
		if (user == null)
			throw new UserNaoEncontradoException("email não encontrado");

		PasswordRecover passwordRecover = new PasswordRecover();
		passwordRecover.setEmail(user.getEmail());
		
		String token = generateToken(6);
		passwordRecover.setToken(token);
		passwordRecover.setExpiration(Instant.now().plusSeconds(tokenMinutos * 60));

	    passwordRecover = recoverRepository.save(passwordRecover);
	    
	    String text = "Para redefinir sua senha, acesse o seguinte link:\n\n" 
	            + recoverUrl + token;
	    
	    emailService.sendEmail( user.getEmail() ,"recuperação de email", text);
	}
	
	/**
	 * Gera um token com um comprimento especificado para melhorar a memorização pelo usuário.
	 * 
	 * @param length O comprimento do token desejado.
	 * @return Uma sequência de caracteres aleatórios com o comprimento especificado.
	 */

	public static String generateToken(int length) {
		StringBuilder token = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			token.append(randomChar);
		}
		return token.toString();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void salvaNovaSenha(NovaSenhaRequest novaSenha) {

		List<PasswordRecover> resultado = recoverRepository.searchValidTokens(novaSenha.getToken() , Instant.now());
		
		if(resultado.isEmpty())
			throw new TokenInvalidoException(novaSenha.getToken());
		
		User user = userRepository.validaEmail(resultado.get(0).getEmail());
		user.setPassword(passwordEncoder.encode(novaSenha.getPassword()));
		userRepository.save(user);
	}

}
