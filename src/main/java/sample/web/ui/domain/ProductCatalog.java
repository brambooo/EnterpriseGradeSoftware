/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import sample.web.ui.domain.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCatalog {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL, orphanRemoval=true)
    private List<Product> products = new ArrayList<>();


    public void add(Product product) {
        products.add(product);
    }

    public Product find(int id) {
        if (id > 0) {
            return products.get(id);
        }
        return null;
    }

}
