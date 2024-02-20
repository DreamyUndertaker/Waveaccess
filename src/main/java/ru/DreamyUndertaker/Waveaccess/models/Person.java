package ru.DreamyUndertaker.Waveaccess.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="person")
public class Person {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "не должно быть пустым")
	@Column(name="username")
	@Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;



	public Person(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id
				+ ", username='" + username + '\''
				+ ", role=" + role
				+ ", password='" + password + '\'' +
				'}';

	}
}
