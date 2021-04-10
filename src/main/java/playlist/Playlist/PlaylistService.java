package playlist.Playlist;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
            this.playlistRepository = playlistRepository;
        }

    public void create(PlaylistDTO playlistDto) {
        playlistRepository.save(
                new PlaylistEntity(playlistDto.getName()));
    }

}
