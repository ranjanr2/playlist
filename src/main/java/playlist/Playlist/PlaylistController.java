package playlist.Playlist;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> CreateAPlaylist(@RequestBody PlaylistDTO playlistDto){
        if (playlistDto == null || playlistDto.getName().trim().isEmpty())
        {
            return new ResponseEntity<>(new Exception("Playlist Name is Required"), HttpStatus.OK);
        }
        else {
            this.service.create(playlistDto);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        }
    }

    @GetMapping("/Playlist")
    public List<PlaylistDTO> getPlaylists() throws Exception {
        return this.service.getAllPlaylists();
    }
    @GetMapping("/Playlist/{name}")
    public ResponseEntity<?> getPlayListByName(@PathVariable String name) {
        PlaylistDTO playlistDto = this.service.getPlaylistByName(name);
        if (playlistDto != null) {
            return new ResponseEntity<>(playlistDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Exception("Requested Playlist does not exist"), HttpStatus.OK);
        }
    }

}
