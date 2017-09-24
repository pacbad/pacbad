package fr.pacbad;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.pacbad.entities.ref.RoleUtilisateurEnum;

@javax.ws.rs.NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AuthNeeded {

	public RoleUtilisateurEnum role() default RoleUtilisateurEnum.JOUEUR;

}
