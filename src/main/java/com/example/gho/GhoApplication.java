package com.example.gho;

import com.example.gho.Dao.PatientRepository;
import com.example.gho.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GhoApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(GhoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        patientRepository.save(new Patient(null,"Mounir",new Date(), true, 5)) ;
        patientRepository.save(new Patient(null,"Amine",new Date(), false,5)) ;
        patientRepository.save(new Patient(null,"Fathallah",new Date(), true,5)) ;
        patientRepository.save(new Patient(null,"Fawzi",new Date(), true,5)) ;
        patientRepository.save(new Patient(null,"Youssef",new Date(), false,5)) ;
        for(int i=1 ; i<+10; i++)
        {
            patientRepository.save(new Patient(null,"Mounir"+i,new Date(), true,5)) ;

        }
    }
}
