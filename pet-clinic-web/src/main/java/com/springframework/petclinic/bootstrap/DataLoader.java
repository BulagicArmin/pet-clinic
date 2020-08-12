package com.springframework.petclinic.bootstrap;


import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
import com.springframework.petclinic.services.SpecialityService;
import com.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Pet types loaded...");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Armin");
        owner1.setLastName("Bulagic");
        owner1.setAddress("Crkvice 32");
        owner1.setCity("Zenica");
        owner1.setPhone("063981211");
        Pet pet1 = new Pet();
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setName("Roki");
        pet1.setPetType(savedDogPetType);
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Amir");
        owner2.setLastName("Huski");
        owner2.setAddress("Poljice");
        owner2.setCity("Zenica");
        owner2.setPhone("0124314212");
        Pet pet2 = new Pet();
        pet2.setOwner(owner2);
        pet2.setName("Djidjo");
        pet2.setPetType(savedCatPetType);
        pet2.setBirthDate(LocalDate.now());
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Doe");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mike");
        vet2.setLastName("Jones");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
