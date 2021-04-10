package playlist.Playlist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class PlaylistTestIT {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    public void createAPlaylist() throws Exception {
        PlaylistDTO playlistDto = new PlaylistDTO("MyPlaylist");
        mockMvc.perform(post("/Playlists/Playlist")
                .content(objectMapper.writeValueAsString(playlistDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()) // Follow Up to andExpect
                .andDo(document("AddPlaylist"));;

    }

    @Test
    @DirtiesContext
    public void getPlaylistsNULLTest() throws Exception {
        mockMvc.perform(get("/Playlists/Playlist")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));

    }
    @Test
    @DirtiesContext
    public void getPlaylistByNameTest() throws Exception {
        PlaylistDTO playlistDto = new PlaylistDTO("MyPlaylist");
        mockMvc.perform(post("/Playlists/Playlist")
                .content(objectMapper.writeValueAsString(playlistDto))
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/Playlists/Playlist/MyPlaylist")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("name").value("MyPlaylist"))
                // Follow Up to andExpect
                .andDo(document("Playlist", responseFields(
                        fieldWithPath("name").description("MyPlaylist")
                )));;
    }
}
