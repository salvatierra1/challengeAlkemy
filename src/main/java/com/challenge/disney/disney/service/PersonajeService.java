package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    void delete(Long id);

    PersonajeDTO editCharacter(Long id, PersonajeDTO edit);

    List<PersonajeDTO> getByFilters(String name, Integer age, Set<Long> movies);

    List<PersonajeBasicDTO> getCharacterBasic();
}
