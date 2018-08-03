/**
 * Lugares.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    idLugar:{
      type: "number"
    },

    tipoLugar:{
      type: "string"
    },

    ubicacionLugar:{
      type: "string"
    },

    horarioAtencionLugar:{
      type: "string"
    }

  },

};

