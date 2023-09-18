/*package ps2.PS2.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import ps2.PS2.dto.MovieDTO;
import ps2.PS2.model.Movie;
import ps2.PS2.repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MovieServiceImplTest {

    private static final String NAME = "Deadpool";
    private static final String GEN = "Comedy";
    private static final String DESCRIPTION = "Comedy ceva";
    private static final String IMAGEPATH = "/cevapath";


    //UUT
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie;


    @BeforeEach
    public void setUp() {
        initMocks(this);
        movie = new Movie();
        movie.setNumeM(NAME);
        movie.setGen(GEN);
        movie.setDescriere(DESCRIPTION);
        movie.setImagePatha(IMAGEPATH);
        when(movieRepository.findByNumeM(NAME)).thenReturn(movie);
    }

    @Test
    public void findByName() {
        movieService = new MovieServiceImpl(movieRepository);
        Movie movie1=movieService.findByName(NAME);
        assertNotNull(movie1);
        assertEquals(NAME,movie1.getNumeM());
    }

    @Test
    public void addMovie() {

        movieService = new MovieServiceImpl(movieRepository);
        MovieDTO movie1 = MovieDTO.builder()
                .numeM(NAME)
                .gen(GEN)
                .descriere(DESCRIPTION)
                .imagePatha(IMAGEPATH)
                .build();
        MovieDTO movie2 = MovieDTO.builder()
                .numeM(NAME)
                .gen(GEN)
                .descriere(DESCRIPTION)
                .imagePatha(IMAGEPATH)
                .build();


        when(movieRepository.save(ArgumentMatchers.any(Movie.class))).thenReturn(movie);

        Movie result = movieService.addMovie(movie1);
        //movie1.setId(result.getId());
        verify(movieRepository).save(ArgumentMatchers.any(Movie.class));
        assertEquals(movie2, result);
    }

    @Test
    public void deleteMovie() {
        movieService = new MovieServiceImpl(movieRepository);
        movieService.deleteMovie(movie.getNumeM(),movie.getGen());
        verify(movieRepository).deleteByNumeMAndGen(movie.getNumeM(),movie.getGen());
    }
}*/