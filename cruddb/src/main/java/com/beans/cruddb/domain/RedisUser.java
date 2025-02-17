package com.beans.cruddb.domain;

import com.beans.cruddb.Enum.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("RedisUser") // This tells Spring Data Redis to store objects under the "RedisUser" key prefix.
public class RedisUser implements Serializable {

//    Redis stores data in a binary format. Marking the class as Serializable ensures
//    that Java can convert it into a byte stream for storage.

//    The id field in RedisUser is of type String rather than Long.
//    This is because Redis typically uses string-based keys for identifying stored objects.
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private Set<Role> roles;
}
