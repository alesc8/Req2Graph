package it.unige.req2graph;

/**
 * Un singolo progetto
 * creato con www.jsonschema2pojo.org 
 * @author Alessandro Scotto
 *
 */

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"description",
"type"
})
public class Project {

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("description")
private String description;
@JsonProperty("type")
private TypeProject type;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

public Project withId(Integer id) {
this.id = id;
return this;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

public Project withName(String name) {
this.name = name;
return this;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

public Project withDescription(String description) {
this.description = description;
return this;
}

@JsonProperty("type")
public TypeProject getTypeProject() {
return type;
}

@JsonProperty("type")
public void setTypeProject(TypeProject type) {
this.type = type;
}

public Project withTypeProject(TypeProject type) {
this.type = type;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public Project withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}