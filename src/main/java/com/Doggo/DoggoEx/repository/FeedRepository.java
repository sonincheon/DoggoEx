package com.Doggo.DoggoEx.repository;

import com.Doggo.DoggoEx.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findByFeedName(String name);
    List<Feed> findByFeedType(String feedType);
}
