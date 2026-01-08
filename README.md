Prise de rendez-vous médical
Contexte : Plateforme de gestion d'agenda médical multi-praticiens.

Domaines métier :

Gestion cabinet médical

Planification rendez-vous

Dossier patient

Services CRUD requis :

Service Praticiens

Entités : Doctor, Specialty, Schedule, Availability

Operations : CRUD médecins, spécialités, créneaux disponibles

Service Patients

Entités : Patient, MedicalRecord, Insurance

Operations : dossiers patients, historique médical

Service Rendez-vous

Entités : Appointment, Consultation, Prescription

Operations : planification RDV, comptes-rendus consultation

Flux Kafka : appointment-booked, reminder-sent, consultation-completed 

Batch : Statistiques mensuelles par spécialité

