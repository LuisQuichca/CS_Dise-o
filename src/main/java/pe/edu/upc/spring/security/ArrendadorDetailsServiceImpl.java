package pe.edu.upc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upc.spring.model.Arrendador;
import pe.edu.upc.spring.repository.IArrendadorRepository;

@Service
public class ArrendadorDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IArrendadorRepository aArrendador;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Arrendador arrendador = aArrendador.findByUsername(username);
		UserBuilder builder = null;
		
		if(arrendador != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(arrendador.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_ARRENDADOR"));
		}
		else {
			throw new UsernameNotFoundException("El usuario no existe");
		}
		return builder.build();
	}

}
