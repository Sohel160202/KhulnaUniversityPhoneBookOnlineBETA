package com.example.khulnauniversityphonebookonlinebeta;

public class Teacher {
    String email;
    String name;
    String post;
    String phone;
    String discipline;
    String profilePic;


    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", phone='" + phone + '\'' +
                ", discipline='" + discipline + '\'' +
                ", profilePic='" + profilePic + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
