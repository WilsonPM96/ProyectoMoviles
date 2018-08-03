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
      model: "Usuarios"
    },

    fecha_ini:{
      type: "string"
    },
    fecha_fin:{
      type: "string"
    },
    detalles_reservas:{
      collection:"DetalleReserva",
      via:"idReserva"
    }

  },

};

