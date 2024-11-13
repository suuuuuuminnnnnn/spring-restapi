package CRUD.example.CRUD.service;

import CRUD.example.CRUD.domain.Member;
import CRUD.example.CRUD.domain.Post;
import CRUD.example.CRUD.dto.request.PostRequestDto;
import CRUD.example.CRUD.dto.response.PostResponseDto;
import CRUD.example.CRUD.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // create
    @Transactional
    public Post save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity());
    }

    public List<PostResponseDto> list(Member member, String nickname) {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);

        }

        return postResponseDtoList;
    }

    public PostResponseDto find(Member member, Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    @Transactional
    public String update(Integer id, PostRequestDto requestDto) {
        String message = "fail";
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        // 작성자만 수정 가능
        if (postRepository.findById(id).get().getMember().equals(requestDto.getMember().getId())) {
            post.update(requestDto.getContent());
            message = "success";
        }

        return message;
    }

    @Transactional
    public String delete(Integer id, Member member) {
        String message = "fail";
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        // 작성자만 삭제 가능
        if (post.getMember().getId().equals(member.getId())) {
            postRepository.delete(post);
            message = "success";
        }

        return message;
    }
}
