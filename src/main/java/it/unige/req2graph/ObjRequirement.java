package it.unige.req2graph;
/**
 * Un singolo requisito
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
"text",
"state",
"errorDescription",
"project"
})
public class ObjRequirement {

@JsonProperty("id")
private Integer id;
@JsonProperty("text")
private String text;
@JsonProperty("state")
private String state;
@JsonProperty("errorDescription")
private Object errorDescription;
@JsonProperty("project")
private Integer project;
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

public ObjRequirement withId(Integer id) {
this.id = id;
return this;
}

@JsonProperty("text")
public String getText() {
return text;
}

@JsonProperty("text")
public void setText(String text) {
this.text = text;
}

public ObjRequirement withText(String text) {
this.text = text;
return this;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

public ObjRequirement withState(String state) {
this.state = state;
return this;
}

@JsonProperty("errorDescription")
public Object getErrorDescription() {
return errorDescription;
}

@JsonProperty("errorDescription")
public void setErrorDescription(Object errorDescription) {
this.errorDescription = errorDescription;
}

public ObjRequirement withErrorDescription(Object errorDescription) {
this.errorDescription = errorDescription;
return this;
}

@JsonProperty("project")
public Integer getProject() {
return project;
}

@JsonProperty("project")
public void setProject(Integer project) {
this.project = project;
}

public ObjRequirement withProject(Integer project) {
this.project = project;
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

public ObjRequirement withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}
@Override
public String toString() {
    return (this.getId().toString()+" "+this.getText());
}

}
