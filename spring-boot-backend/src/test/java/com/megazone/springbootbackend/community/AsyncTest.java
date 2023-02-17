package com.megazone.springbootbackend.community;

import com.megazone.springbootbackend.community.model.entity.Article;
import com.megazone.springbootbackend.community.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

@ExtendWith(MockitoExtension.class)
public class AsyncTest {

    @Mock
    ArticleRepository articleRepository;

        @Test
        public void kafkaTest() {
            Article article = ArticleRepository.findById(Long.valueOf(1));

            viewCount(1);

            System.out.println("================= 게시글 조회 결과 =================");
            System.out.println("게시글 제목 >>>>>>>> " + article.getTitle());

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

        @Async // 조회수 증가 메세지 보낼 메서드
        public void viewCount(long ArticleId) {
            kafkaProducerConfig.;
        }
    }
}
