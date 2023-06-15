package com.asset.authorization_server.entity.authorization_server;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "client_name", unique = true)
    private String clientName;

    @Column(name = "client_id", unique = true)
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "client_authentication_method_mapping",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authentication_method_id", referencedColumnName = "id")}
    )
    private List<AuthenticationMethod> authenticationMethods = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "client_grant_type_mapping",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "grant_type_id", referencedColumnName = "id")}
    )
    private List<GrantType> grantTypes = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "client_scope_map",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "scope_id", referencedColumnName = "id")}
    )
    private List<Scope> scopes = new ArrayList<>();

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "client_redirect_uri_map",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "redirect_uri_id", referencedColumnName = "id")}
    )
    private List<RedirectURI> redirectURIs = new ArrayList<>();

    @OneToOne(mappedBy = "client")
    private TokenSettings tokenSettings;
}
