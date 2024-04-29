package matteofurgani.Capstone.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;

@SpringBootApplication
public class CapstoneProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(CapstoneProjectApplication.class, args);

		LocalTime l1 = LocalTime.now();

		System.out.println(LocalTime.of(l1.getHour(), l1.getMinute()));
	}

}
