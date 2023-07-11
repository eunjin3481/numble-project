package com.numble.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UserVO {
	private int id;
	private String name;
	private String birthDay;
	private String token;
}
