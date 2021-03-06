### [**🏠 Home**](/README.md)

###  [**⬅️ Back**](external-services.md)
-----
# Agenzia ACME

L'agenzia è stata implementata tramite un file BPMN eseguibile e due web service usati come interfaccia per i client.

## Model [(sources)](https://github.com/loopingdoge/acme-agency/tree/master/src/acme-model)
Le classi condivise utilizzate come modello per i dati sono state aggregate in un file `.jar` ed incluse come dipendenze Maven di entrambi i progetti.


## Web services [(sources)](https://github.com/loopingdoge/acme-agency/blob/master/src/acme-agency-ws)

I web services sono stati realizzati utilizzando JAX-WS ed espongono le interfacce utilizzate dai client di acquirente e venditore per scambiare informazioni con il BPMS. Di seguito sono descritti gli enpoint per ciascuno:

### Acquirente [(sources)](https://github.com/loopingdoge/acme-agency/blob/master/src/acme-agency-ws/src/main/java/org/loopingdoge/acme/BuyerWebService.java)

- `requestHouses`
  avvia una istanza di processo per l'acquirente e restituisce una lista di case disponibili in linea con i criteri di ricerca

- `houseProposalReply`
  permette di accettare una casa, richiederne altre o terminare la ricerca

- `getSellerMeetingDateList`
  restituisce l'elenco delle date in cui il venditore è disponibile per mostrare la casa

- `replyToMeetingProposal`
  permette di accettare o rifiutare le date di incontro proposte dal venditore

- `makeOffer`
  permette di avanzare un'offerta di acquisto per una casa

- `getChosenHouse`
  restituisce i dati della casa scelta dall'acquirente

### Venditore [(sources)](https://github.com/loopingdoge/acme-agency/blob/master/src/acme-agency-ws/src/main/java/org/loopingdoge/acme/SellerWebService.java)
- `proposeHouse`
  permette di mettere in vendita una casa ed avvia un'istanza di processo per il venditore

- `sendAvailability`
  permette di comunicare ad ACME in quali date il venditore è disponibile per mostrare la casa

- `getBuyerMeetingDateList`
  restituisce l'elenco delle date di incontro proposte dall'acquirente

- `confirmMeeting`
  permette di confermare o rifiutare le date di incontro proposte dall'acquirente

- `getOffer`
  permette di visionare un'offerta di acquisto

- `offerReply`
  permette di accettare o rifiutare un'offerta di acquisto


## BPMS [(sources)](https://github.com/loopingdoge/acme-agency/blob/master/src/acme-agency)

Il BPMS è semplicemente un progetto Maven che implementa i task specificati dal BPM ed è organizzato come segue:

- [Services](https://github.com/loopingdoge/acme-agency/tree/master/src/acme-agency/src/main/java/org/loopingdoge/acme/services), contiene le classi Java che implementano la logica dei diversi task
- [Utils](https://github.com/loopingdoge/acme-agency/tree/master/src/acme-agency/src/main/java/org/loopingdoge/acme/utils), contiene le interfacce per i servizi REST ed i database delle case e dei notai
- [Forms](https://github.com/loopingdoge/acme-agency/tree/master/src/acme-agency/src/main/webapp/forms), contiene i form utilizzati per gli user task in camunda



Per semplicità i database sono stati implementati come classi java statiche.

-----
### [**➡️ Next**](clients.md)