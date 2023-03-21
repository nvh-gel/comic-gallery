package com.eden.gallery.model;

import com.eden.data.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "update comic set is_deleted=true, updated_at=CURRENT_TIMESTAMP where id=?")
@Where(clause = "is_deleted=false")
public class Comic extends BaseModel {
    String name;
    String author;
    String description;
}
