/**
 * Reservas.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    idReserva:{
      type: "number"
    },
    idUsuario:{
      type: "number"
    },

    fecha_ini:{
      type: "string"
    },
    fecha_fin:{
      type: "string"
    }

  },

};

