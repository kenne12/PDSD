-- Table: public.tachedistrict

-- DROP TABLE public.tachedistrict;

CREATE TABLE public.tachedistrict
(
  idtachedistrict bigint NOT NULL,
  libelle text,
  responsable character varying,
  cout double precision,
  m1 boolean,
  m2 boolean,
  m3 boolean,
  m4 boolean,
  m5 boolean,
  m6 boolean,
  m7 boolean,
  m8 boolean,
  m9 boolean,
  m10 boolean,
  m11 boolean,
  m12 boolean,
  idactivitestructure bigint,
  idannee integer,
  CONSTRAINT pk_tachedistrict PRIMARY KEY (idtachedistrict),
  CONSTRAINT fk_activite_tachedistrict FOREIGN KEY (idactivitestructure)
      REFERENCES public.activite_structure (idactivite_structure) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_annee_tachedistrict FOREIGN KEY (idannee)
      REFERENCES public.annee (idannee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tachedistrict
  OWNER TO postgres;

-- Index: public.fki_activite_tachedistrict

-- DROP INDEX public.fki_activite_tachedistrict;

CREATE INDEX fki_activite_tachedistrict
  ON public.tachedistrict
  USING btree
  (idactivitestructure);

-- Index: public.fki_annee_tachedistrict

-- DROP INDEX public.fki_annee_tachedistrict;

CREATE INDEX fki_annee_tachedistrict
  ON public.tachedistrict
  USING btree
  (idannee);

-----------------------------------------------------------------------------------------------------------------

-- Table: public.tacheregion

-- DROP TABLE public.tacheregion;

CREATE TABLE public.tacheregion
(
  idtacheregion bigint NOT NULL,
  libelle character varying,
  cout double precision,
  responsable character varying,
  m1 boolean,
  m2 boolean,
  m3 boolean,
  m4 boolean,
  m5 boolean,
  m6 boolean,
  m7 boolean,
  m8 boolean,
  m9 boolean,
  m10 boolean,
  m11 boolean,
  m12 boolean,
  idactivitestructure bigint,
  idannee integer,
  CONSTRAINT pk_tacheregion PRIMARY KEY (idtacheregion),
  CONSTRAINT fk_activite_tacheregion FOREIGN KEY (idactivitestructure)
      REFERENCES public.activite_structure_region (idactivite_structure_region) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_annee_tacheregion FOREIGN KEY (idannee)
      REFERENCES public.annee (idannee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tacheregion
  OWNER TO postgres;

-----------------------------------------------------------------------------------------------------------------------

-- Column: valeurrealisee

-- ALTER TABLE public.cible DROP COLUMN valeurrealisee;

ALTER TABLE public.cible ADD COLUMN valeurrealisee double precision;

-- Column: commentaire

-- ALTER TABLE public.cible DROP COLUMN commentaire;

ALTER TABLE public.cible ADD COLUMN commentaire text;


-- Column: recommandation

-- ALTER TABLE public.cible DROP COLUMN recommandation;

ALTER TABLE public.cible ADD COLUMN recommandation text;


-- Column: etat

-- ALTER TABLE public.cible DROP COLUMN etat;

ALTER TABLE public.cible ADD COLUMN etat boolean;

-- Column: ecart

-- ALTER TABLE public.cible DROP COLUMN ecart;

ALTER TABLE public.cible ADD COLUMN ecart double precision;


-- Column: evaluee

-- ALTER TABLE public.cible DROP COLUMN evaluee;

ALTER TABLE public.cible ADD COLUMN evaluee boolean;
ALTER TABLE public.cible ALTER COLUMN evaluee SET DEFAULT false;



----------------------------------------------------------------------------------------------------------------------


-- Column: valeurrealisee

-- ALTER TABLE public.cible_region DROP COLUMN valeurrealisee;

ALTER TABLE public.cible_region ADD COLUMN valeurrealisee double precision;


-- Column: commentaire

-- ALTER TABLE public.cible_region DROP COLUMN commentaire;

ALTER TABLE public.cible_region ADD COLUMN commentaire text;

-- Column: recommandation

-- ALTER TABLE public.cible_region DROP COLUMN recommandation;

ALTER TABLE public.cible_region ADD COLUMN recommandation text;


-- Column: etat

-- ALTER TABLE public.cible_region DROP COLUMN etat;

ALTER TABLE public.cible_region ADD COLUMN etat boolean;