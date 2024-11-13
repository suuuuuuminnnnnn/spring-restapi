package CRUD.example.CRUD.controller;

import CRUD.example.CRUD.domain.Post;
import CRUD.example.CRUD.dto.request.PostRequestDto;
import CRUD.example.CRUD.dto.response.PostResponseDto;
import CRUD.example.CRUD.repository.PostRepository;
import CRUD.example.CRUD.security.SecurityUser;
import CRUD.example.CRUD.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping(value = "/create")
    public String create(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal) {
        // 프론트에서 받은 토큰으로 Member 구분함
        requestDto.setMember(principal.getMember());
        postService.save(requestDto);

        return "success";
    }

    @GetMapping()
    public List<PostResponseDto> list(@AuthenticationPrincipal SecurityUser principal, @RequestParam(value ="nickname", required = false) String nickname) {
        return postService.list(principal.getMember(), nickname);
    }

    @GetMapping("/{id}")
    public PostResponseDto get(@AuthenticationPrincipal SecurityUser principal, @PathVariable Integer id) {
        return postService.find(principal.getMember(), id);
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable Integer id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal SecurityUser principal) {
        requestDto.setMember(principal.getMember());
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id, @AuthenticationPrincipal SecurityUser principal) {
        return postService.delete(id, principal.getMember());
    }

}
