package cosmos.frontend.middle.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class DataStream{

	private String name;
	private double currentValue;
	private Date lastUpdate;
	
}