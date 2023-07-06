package bg.softuni.gamestoremappingexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class GameEditDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String thubnailURL;
    private String description;
    private LocalDate releaseDate;

    public String successfullyEditedGame() {
        return "Edited " + this.title;
    }

    public void updateFields(Map<String, String> updatedValues) {
        for (String key : updatedValues.keySet()) {
            if (Objects.equals(key, "title")) {
                setTitle(updatedValues.get(key));
            } else if (Objects.equals(key, "price")) {
                setPrice(new BigDecimal(updatedValues.get(key)));
            } else if (Objects.equals(key, "size")) {
                setSize(Float.parseFloat(updatedValues.get(key)));
            } else if (Objects.equals(key, "trailer")) {
                setTrailer(updatedValues.get(key));
            } else if (Objects.equals(key, "thubnailURL")) {
                setThubnailURL(updatedValues.get(key));
            } else if (Objects.equals(key, "description")) {
                setDescription(updatedValues.get(key));
            } else if (Objects.equals(key, "releaseDate")) {
                setReleaseDate(LocalDate.now());
            }
        }
    }
}
