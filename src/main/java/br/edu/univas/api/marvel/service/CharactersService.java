package br.edu.univas.api.marvel.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.univas.api.marvel.configuration.RestTemplateConfig;
import br.edu.univas.api.marvel.exception.MarvelApiException;

@Service
public class CharactersService {

    private static final String MARVEL_PUBLIC_KEY = "3d226836ebc4c4aa956eb5782bedff43";
    private static final String MARVEL_PRIVATE_KEY = "60ba957ae87caa8b496cdb2da24bc99ecc4e9bba";

    private static String marvelAllCharactersUrl = "https://gateway.marvel.com:443/v1/public/characters?ts=%d&apikey=%s&hash=%s";
    private static String marvelCharacterByIdUrl = "https://gateway.marvel.com:443/v1/public/characters/%d?ts=%d&apikey=%s&hash=%s";
    private static String marvelComicByCharacterIdUrl = "https://gateway.marvel.com:443/v1/public/characters/%d/comics?ts=%d&apikey=%s&hash=%s";
    private static String marvelEventsByCharacterIdUrl = "https://gateway.marvel.com:443/v1/public/characters/%d/events?ts=%d&apikey=%s&hash=%s";
    private static String marvelSeriesByCharacterIdUrl = "https://gateway.marvel.com:443/v1/public/characters/%d/series?ts=%d&apikey=%s&hash=%s";
    private static String marvelStoriesCharacterIdUrl = "https://gateway.marvel.com:443/v1/public/characters/%d/stories?ts=%d&apikey=%s&hash=%s";
    

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public ResponseEntity<String> listAllCharacters() throws MarvelApiException {
        RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
        return restTemplate.getForEntity(getAllCharactersUrl(), String.class);
    }

     public ResponseEntity<String> listCharacterById(long characterId) throws MarvelApiException {
     RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
     return restTemplate.getForEntity(getCharacterByIdUrl(characterId), String.class);
     }
    
     public ResponseEntity<String> listAllComicsByCharacterById(long characterId) throws MarvelApiException {
     RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
     return restTemplate.getForEntity(getComicsByCharacterIdUrl(characterId), String.class);
     }
    
     public ResponseEntity<String> listAllEventsByCharacterById(long characterId) throws MarvelApiException {
     RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
     return restTemplate.getForEntity(getEventsByCharacterIdUrl(characterId), String.class);
     }
    
     public ResponseEntity<String> listAllSeriesByCharacterById(long characterId) throws MarvelApiException {
     RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
     return restTemplate.getForEntity(getSeriesByCharacterIdUrl(characterId), String.class);
     }
    
     public ResponseEntity<String> listAllStoriesByCharacterById(long characterId) throws MarvelApiException {
     RestTemplate restTemplate = restTemplateConfig.createRestTemplate();
     return restTemplate.getForEntity(getStoriesByCharacterIdUrl(characterId), String.class);
     }

    public void validateCharacterId(long characterId) throws MarvelApiException {
        if (characterId <= 0) {
            throw new MarvelApiException("O parametro characterId é invalido.", HttpStatus.BAD_REQUEST);
        }
    }

    private String getAllCharactersUrl() throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelAllCharactersUrl,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String getCharacterByIdUrl(long id) throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelCharacterByIdUrl,
                        id,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String getComicsByCharacterIdUrl(long id) throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelComicByCharacterIdUrl,
                        id,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String getEventsByCharacterIdUrl(long id) throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelEventsByCharacterIdUrl,
                        id,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String getSeriesByCharacterIdUrl(long id) throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelSeriesByCharacterIdUrl,
                        id,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String getStoriesByCharacterIdUrl(long id) throws MarvelApiException {
        long timestamp = Instant.now().toEpochMilli();
        return new StringBuilder()
                .append(String.format(marvelStoriesCharacterIdUrl,
                        id,
                        timestamp,
                        MARVEL_PUBLIC_KEY,
                        generateHash(timestamp)))
                .toString();
    }

    private String generateHash(long timestamp) throws MarvelApiException {
        String key = new StringBuilder().append(timestamp).append(MARVEL_PRIVATE_KEY).append(MARVEL_PUBLIC_KEY).toString();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(key.getBytes(), 0, key.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new MarvelApiException("Não foi possivel gerar o hash da requisição", HttpStatus.PRECONDITION_FAILED);
        }
    }

}