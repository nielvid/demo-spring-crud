package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import javax.validation.constraints.NotBlank;

public class User {
        private final UUID id;

//        @NotBlank
        private final String name;

        public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public String toString() {
            return String.format(
                    "User[id=%s, name='%s']",
                    id, name);
        }
        public UUID getId() {
            return id;
        }
        public String getName() {
            return name;
        }



    }

