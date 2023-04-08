package com.softuni.shoestrade.service;

import com.softuni.shoestrade.model.Comment;
import com.softuni.shoestrade.model.UserEntity;
import com.softuni.shoestrade.model.dto.CommentDTO;
import com.softuni.shoestrade.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    private OfferService offerService;

    @Autowired
    public CommentService(CommentRepository commentRepository, OfferService offerService) {
        this.commentRepository = commentRepository;
        this.offerService = offerService;
    }

    public List<Comment> getCommentsByOffer(Long offerId) {
        return this.commentRepository.findCommentsByOfferId(offerId);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found!"));
    }

    public Comment deleteCommentById(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
        return comment;
    }


        public Comment createComment(CommentDTO commentDto, Long offerId, UserEntity author) {
            Comment comment = new Comment();
            comment.setCreated(LocalDateTime.now());
            comment.setOffer(offerService.getOfferById(offerId));
            comment.setAuthor(author);
            comment.setText(commentDto.getText());
            comment.setApproved(true);

            commentRepository.save(comment);

            return comment;
        }
    }

