package playlist.Playlist;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor

public class PlaylistDTO {
    String name;

    public PlaylistDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
