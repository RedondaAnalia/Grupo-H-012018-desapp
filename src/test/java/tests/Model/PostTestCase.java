package tests.Model;

import static org.junit.Assert.*;

import builders.PostBuilder;
import model.Post;
import model.exceptions.TimeOutOfRangeException;
import model.exceptions.UserBlockedException;
import org.junit.Test;

import java.time.LocalDateTime;

public class PostTestCase {

    @Test(expected=TimeOutOfRangeException.class)
    public void shouldThrowAnExceptionWhenAPostHasMoreThan5Days(){
        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MAX).build();
    }

    @Test(expected=TimeOutOfRangeException.class)
    public void shouldThrowAnExceptionWhenAPostHasLessThan1Hour(){
        Post post = PostBuilder.
                aPost().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.MIN).build();
    }

    @Test(expected=UserBlockedException.class)
    public void shouldThrowAnExceptionWhenAUserIsDIsabled(){
        Post post = PostBuilder.
                aPost().
                withUserDisabled().
                withSinceDate(LocalDateTime.now()).
                withUntilDate(LocalDateTime.now().plusDays(3L)).build();
    }
}
