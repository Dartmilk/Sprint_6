import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    private static final String MALE = "Самец";
    private static final String UNSUPPORTED_SEX = "unsupported sex";
    private static final String TEXT_EXCEPTION = "Используйте допустимые значения пола животного - самей или самка";
    private Lion lion;
    @Mock
    private Feline feline;

    @Test
    public void testGetKittens() throws Exception {
        lion = new Lion(MALE, feline);

        lion.getKittens();
        Mockito.verify(feline).getKittens();
    }

    @Test
    public void testUnsupportedSexThrowsException() {
        Throwable throwable = assertThrows(Exception.class, () -> lion = new Lion(UNSUPPORTED_SEX, feline));
        assertThat(throwable).hasMessage(TEXT_EXCEPTION);
    }

    @Test
    public void testGetFood() throws Exception {
        Lion lion = new Lion(MALE, feline);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood(Mockito.eq("Хищник"))).thenReturn(expectedFood);

        List<String> actual = lion.getFood();

        assertEquals(expectedFood, actual);
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }
}