package br.edu.univas.api.marvel.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.univas.api.marvel.exception.MarvelApiException;
import br.edu.univas.api.marvel.service.CharactersService;

@SpringBootTest
public class CharactersControllerTest {

    private static final long CHARACTER_ID = 1011334;

    @Mock
    private CharactersService service;

    @InjectMocks
    private CharactersController controller;

    @Test
    public void listAllCharactersShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listAllCharacters()).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listAllCharacters();

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listAllCharacters();
    }

    @Test
    public void listCharacterByIdWhenCharacterExistShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listCharacterById(CHARACTER_ID)).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listCharacterById(CHARACTER_ID);
    }

    @Test
    public void listAllComicsByCharacterByIdWhenCharacterExistShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listAllComicsByCharacterById(CHARACTER_ID)).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listAllComicsByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listAllComicsByCharacterById(CHARACTER_ID);
    }

    @Test
    public void listAllEventsByCharacterByIdWhenCharacterExistShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listAllEventsByCharacterById(CHARACTER_ID)).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listAllEventsByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listAllEventsByCharacterById(CHARACTER_ID);
    }

    @Test
    public void listAllSeriesByCharacterByIdWhenCharacterExistShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listAllSeriesByCharacterById(CHARACTER_ID)).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listAllSeriesByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listAllSeriesByCharacterById(CHARACTER_ID);
    }

    @Test
    public void listAllStoriesByCharacterByIdWhenCharacterExistShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(service.listAllStoriesByCharacterById(CHARACTER_ID)).thenReturn(expectedResponse);

        // when
        ResponseEntity<String> actualResponse = controller.listAllStoriesByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).listAllStoriesByCharacterById(CHARACTER_ID);
    }

}
