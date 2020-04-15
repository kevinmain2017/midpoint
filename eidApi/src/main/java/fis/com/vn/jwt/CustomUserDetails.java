package fis.com.vn.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import fis.com.vn.table.MUser;
import lombok.Data;

@SuppressWarnings("serial")
@Data
//@AllArgsConstructor
public class CustomUserDetails implements UserDetails{
	MUser user;
	public CustomUserDetails(MUser user2) {
		this.user = user2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		return null;
	}
	
	//mac dinh ko check password de la ""
	@Override
	public String getPassword() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode("");
	}

	@Override
	public String getUsername() {
		return user.getTenDangNhap();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
//		if(user.getStatus().equals("01")) {
			return true;
//		} else {
//			return false;
//		}
	}

}
