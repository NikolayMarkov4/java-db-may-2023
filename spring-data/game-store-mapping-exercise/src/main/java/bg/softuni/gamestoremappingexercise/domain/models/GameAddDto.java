package bg.softuni.gamestoremappingexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class GameAddDto {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto(String title, BigDecimal price, float size, String trailer, String thubnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = thubnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
        validate();
    }

    private void validate() {
        // TODO: validate game fields entries
    }

    public String successfullyAddedGame() {
        return "Added " + this.title;
    }
}

