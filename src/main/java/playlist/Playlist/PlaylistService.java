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

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistEntity -> {
                    return new PlaylistDTO(playlistEntity.getName());
                })
                .collect(Collectors.toList());
    }

    public PlaylistDTO getPlaylistByTitle(String title) {
        return playlistRepository.findAll()
                .stream()
                .filter(playlistEntity -> playlistEntity.getName().equals(title))
                .map(playlistEntity -> { return new PlaylistDTO(playlistEntity.getName());
                }).collect(Collectors.toList())
                .stream().findFirst().orElse(null);
    }

    public PlaylistDTO getPlaylistByName(String name) {
        return playlistRepository.findAll()
                .stream()
                .filter(playlistEntity -> playlistEntity.getName().equals(name))
                .map(playlistEntity -> {
                    return new PlaylistDTO(playlistEntity.getName());
                }).collect(Collectors.toList())
                .stream().findFirst().orElse(null);
    }

}
