package fis.com.vn.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fis.com.vn.repository.MUserRepository;
import fis.com.vn.table.MUser;


@Service
public class UserService implements UserDetailsService{
	@Autowired
    private MUserRepository mUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MUser user = mUserRepository.findByTenDangNhap(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
	}

}
