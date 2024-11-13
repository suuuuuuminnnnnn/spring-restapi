package CRUD.example.CRUD.dto.response;

import CRUD.example.CRUD.domain.Post;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

@Getter
@ToString
public class PostResponseDto {
    private Integer id;
    private String content;
    private String nickname;
    private String profilePath;
    private String createdAt;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.nickname = entity.getMember().getNickname();
        this.content = entity.getContent();
        this.profilePath = entity.getMember().getProfilePath();
        this.createdAt = this.createdAt = entity.getCreatedAt().format(String.valueOf(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    }
}
