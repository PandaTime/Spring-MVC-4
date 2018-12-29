package com.springbook.profile;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springbook.date.PastLocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.time.LocalDate;
/**
 * ProfileForm
 */
public class ProfileForm {
  @Size(min=2)
  private String twitterHandle;

  @Email
  @NotEmpty
  private String email;

  @NotNull
  @PastLocalDate
  private LocalDate birthDate;

  @NotEmpty
  private List<String> tastes = new ArrayList<>();

  /**
   * @param twitterHandle the twitterHandle to set
   */
  public void setTwitterHandle(String twitterHandle) {
    this.twitterHandle = twitterHandle;
  }
  /**
   * @return the twitterHandle
   */
  public String getTwitterHandle() {
    return twitterHandle;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param birthDate the birthDate to set
   */
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  /**
   * @return the birthDate
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }
  /**
   * @param tastes the tastes to set
   */
  public void setTastes(List<String> tastes) {
    this.tastes = tastes;
  }
  /**
   * @return the tastes
   */
  public List<String> getTastes() {
    return tastes;
  }

}