SELECT        Albaranes.NAlbaran AS numeroAlbaran, Albaranes.Empresa AS serie, Albaranes.FechaDosificacion AS fechafabricacion, Albaranes.Boca AS destino, Albaranes.Via, Albaranes.TipoDescarga, 
                         Albaranes.VelocidadDescarga, Albaranes.PesoTotalReal , AlbaranesExportacion.[Codigo-Obra] AS obra, AlbaranesExportacion.[Codigo-Camion], AlbaranesExportacion.[ContCemReal-Formula], 
                         AlbaranesExportacion.[Denominacion-Formula] AS nombreformula, AlbaranesExportacion.[Resistencia-Formula], [DetallesAlbaranesExportacion-Materiales].[PesoReal-FormulaPeso] AS cantidadreal, 
                         CamionesAlbaranes.Matricula AS matricula, AlbaranesExportacion.VolDosificar AS cantidadfabricada, AlbaranesExportacion.VolFacturar AS cantidadAlbaran, 
                         AlbaranesExportacion.EstadoAlbaran AS estadoalbaran, [DetallesAlbaranesExportacion-Materiales].[Humedad-FormulaPeso] AS humedad, Materiales.Identidad AS codigomaterial, 
                         Materiales.Material AS nombrematerial, Materiales.Tipo AS materialtipoid, [DetallesAlbaranesExportacion-Materiales].Pesada, [DetallesAlbaranesExportacion-Materiales].CodigoElemento, 
                         Materiales.Codigo
FROM            Albaranes INNER JOIN
                         AlbaranesExportacion ON Albaranes.NAlbaran = AlbaranesExportacion.NAlbaran INNER JOIN
                         [DetallesAlbaranesExportacion-Materiales] ON Albaranes.NAlbaran = [DetallesAlbaranesExportacion-Materiales].NAlbaran INNER JOIN
                         CamionesAlbaranes ON Albaranes.Id = CamionesAlbaranes.IdAlbaran INNER JOIN
                         Materiales ON ([DetallesAlbaranesExportacion-Materiales].NombreElemento = Materiales.Material and
						 [DetallesAlbaranesExportacion-Materiales].CodigoElemento = Materiales.Codigo ) where (Albaranes.FechaDosificacion > ? and Albaranes.FechaDosificacion < ?)