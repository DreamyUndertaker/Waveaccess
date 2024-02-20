package ru.DreamyUndertaker.Waveaccess.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "talk")
public class Talk {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "не должно быть пустым")
	@Column(name="audience_number")
	@Size(min = 1, max = 100)
	private int audience_number;
	@Column(name="time")
	private ZonedDateTime time;
	@Column(name="speaker_number")
	private int speaker_number;



	public Talk(int audience_number) {
		this.audience_number = audience_number;
	}

	@Override
	public String toString() {
		return "Talk{" +
				"id=" + id
				+ ", audience_number='" + audience_number + '\''
				+ ", time=" + time
				+ ", speaker_number='" + speaker_number + '\'' +
				'}';

	}
}
