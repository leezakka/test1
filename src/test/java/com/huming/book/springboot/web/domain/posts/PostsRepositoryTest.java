package com.huming.book.springboot.web.domain.posts;

import com.huming.book.springboot.domain.posts.Posts;
import com.huming.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //단위 테스트가 끝날때 수행되는 메소드를 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void ArticleWrite_Load() {
        //given
        String title = "Test Article";
        String content = "Test Body";

        postsRepository.save(Posts.builder()  // 테이블 posts에 insert/update 실행
                .title(title)
                .content(content)
                .author("leezakka@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts의 모든 데이터를 select all.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}
