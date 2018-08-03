/**
 * DetalleReserva.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    idReserva:{
      model:"Reservas"
    },
    idLugar:{
      model:"Lugares"
    },
    estado:{
      type: "boolean"
    },
    fecha:{
      type: "string"
    },
    hora_ini:{
      type: "string"
    },
    hora_fin:{
      type: "string"
    },

  },

};

