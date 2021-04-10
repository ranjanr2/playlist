package playlist.Playlist;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@NoArgsConstructor
@RequestMapping("/Playlists")
public class PlaylistController {

    @Autowired
    PlaylistService service;

    @PostMapping("/Playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateAPlaylist(@RequestBody PlaylistDTO playlistDto){
        this.service.create(playlistDto);
    }

    @GetMapping("/Playlist")
    public List<PlaylistDTO> getAllMovies() throws Exception {
        return this.service.getAllPlaylists();
    }

}
