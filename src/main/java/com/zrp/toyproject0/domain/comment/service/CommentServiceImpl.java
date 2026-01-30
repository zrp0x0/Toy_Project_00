package com.zrp.toyproject0.domain.comment.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zrp.toyproject0.domain.comment.dto.CommentRequest;
import com.zrp.toyproject0.domain.comment.dto.CommentResponse;
import com.zrp.toyproject0.domain.comment.entity.Comment;
import com.zrp.toyproject0.domain.comment.repository.CommentRepository;
import com.zrp.toyproject0.domain.item.entity.Item;
import com.zrp.toyproject0.domain.item.repository.ItemRepository;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public void createComment(CommentRequest commentRequest, Authentication auth) {
        
        Optional<Item> result1 = itemRepository.findById(commentRequest.getItemId());
        Optional<Member> result2 = memberRepository.findByUsername(auth.getName());

        if (result1.isPresent() && result2.isPresent()) {
            Comment comment = commentRequest.toEntity(result1.get(), result2.get());
            commentRepository.save(comment);
        } else {
            return;
        }
    }

    @Override
    public Page<CommentResponse> findByItemId(Long itemId, Pageable pageable) {
        Page<Comment> result = commentRepository.findByItemId(itemId, pageable);
        return result.map(CommentResponse::from);
    }
    
}
