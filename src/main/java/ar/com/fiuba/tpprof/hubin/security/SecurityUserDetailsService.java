package ar.com.fiuba.tpprof.hubin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ar.com.fiuba.tpprof.hubin.model.Alumno;
import ar.com.fiuba.tpprof.hubin.repository.AlumnoDao;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	AlumnoDao alumnoDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Alumno alumno = alumnoDao.findByUsername(username);
		if (alumno != null) {
			return new User(alumno.getUsername(), alumno.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException("No se encontro al usuario '" + username + "'");
		}
	}

}
