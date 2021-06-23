package br.edu.univas.api.marvel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.edu.univas.api.marvel.configuration.RestTemplateConfig;
import br.edu.univas.api.marvel.exception.MarvelApiException;

@SpringBootTest
public class CharactersServiceTest {

    private static final long CHARACTER_ID = 1011334;
    private static final long INVALID_CHARACTER_ID = -1;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateConfig restTemplateConfig;

    @InjectMocks
    private CharactersService service;

    @Test
    public void listAllCharactersShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        ResponseEntity<String> actualResponse = service.listAllCharacters();

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void listCharactersByIdWhenCharacterExistsShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        ResponseEntity<String> actualResponse = service.listCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void listAllComicsByCharacterIdWhenCharacterExistsShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        ResponseEntity<String> actualResponse = service.listAllComicsByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void listAllEventsByCharacterIdWhenCharacterExistsShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        ResponseEntity<String> actualResponse = service.listAllEventsByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void listAllSeriesByCharacterIdWhenCharacterExistsShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        ResponseEntity<String> actualResponse = service.listAllSeriesByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void listAllStoriesByCharacterIdWhenCharacterExistsShouldReturnHttpStatusOk() throws MarvelApiException {
        // given
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        Mockito.when(restTemplateConfig.createRestTemplate()).thenReturn(restTemplate);
        BDDMockito.given(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.<Class<String>> any())).willReturn(
                expectedResponse);

        // when
        ResponseEntity<String> actualResponse = service.listAllStoriesByCharacterById(CHARACTER_ID);

        // then
        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void validateCharacterIdWhenCharacterIdInvalidShouldThrowException() throws MarvelApiException {
        // when
        Exception exception = Assertions.assertThrows(MarvelApiException.class, () -> service.validateCharacterId(INVALID_CHARACTER_ID));

        // then
        Assertions.assertEquals("O parametro characterId é invalido.", exception.getMessage());
    }

}
