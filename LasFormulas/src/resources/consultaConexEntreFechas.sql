SELECT        albaranes.albaranid, albaranes.fabricacion, albaranes.serie, albaranes.numeroalbaran, albaranes.momento AS fechafabricacion, albaranes.cantidadalbaran, albaranes.cantidadfabricada, material.materialid, 
                         material.codigo AS codigomaterial, material.materialdesc AS nombrematerial, humedades.humedad, teoricosreales.ciclo, teoricosreales.teorico AS cantidadteorica, teoricosreales.cantidad AS cantidadreal, 
                         unidad.unidadabreviatura AS unidadabrev, clientes.razonsocial AS nombrecliente, obras.descripcion AS nombreobra, formulas.descripcion AS nombreformula, 
                         CASE WHEN unidad.magnitudid = 'Masa' THEN teoricosreales.teorico * unidad.factorconversion ELSE teoricosreales.teorico * unidad.factorconversion * material.densidadreal END AS cantidadteoricaparatotal, 
                         CASE WHEN unidad.magnitudid = 'Masa' THEN teoricosreales.cantidad * unidad.factorconversion ELSE teoricosreales.cantidad * unidad.factorconversion * material.densidadreal END AS cantidadrealparatotal, 
                         teoricosreales.cantidad - teoricosreales.teorico AS diferenciacantidad, 
                         (CASE WHEN unidad.magnitudid = 'Masa' THEN teoricosreales.cantidad * unidad.factorconversion ELSE teoricosreales.cantidad * unidad.factorconversion * material.densidadreal END) 
                         - (CASE WHEN unidad.magnitudid = 'Masa' THEN teoricosreales.teorico * unidad.factorconversion ELSE teoricosreales.teorico * unidad.factorconversion * material.densidadreal END) AS diferenciatotal, 
                         estadoalbaran.descripcion AS descripcionestadoalbaran, estadofabricacion.descripcion AS descripcionestadofabricacion, teoricosreales.nombrelocalizacion, teoricosreales.descripcionmotivo, material.marcadoCE, 
                         material.materialtipoid, materialtipo.materialtipodesc, albaranes.obra, camiones.matricula, albaranes.estadoalbaran, albaranes.planta, albaranes.destino
FROM            estadofabricacion RIGHT OUTER JOIN
                         fabricaciones RIGHT OUTER JOIN
                         series RIGHT OUTER JOIN
                         albaranes AS albaranes INNER JOIN
                         camiones ON albaranes.camion = camiones.camionid ON series.serieid = albaranes.serie ON fabricaciones.fabricacionid = albaranes.fabricacion ON 
                         estadofabricacion.estadofabricacionid = fabricaciones.estadofabricacionid LEFT OUTER JOIN
                         estadoalbaran ON albaranes.estadoalbaran = estadoalbaran.estadoalbaranid LEFT OUTER JOIN
                         unidad RIGHT OUTER JOIN
                             (SELECT        consumos.fabricacionid, consumos.ciclo, salidas.localizacionid, salidas.materialid, salidas.unidad, consumos.teorico, salidas.cantidad, localizacion.descripcion AS nombrelocalizacion, 
                                                         motivo.descripcion AS descripcionmotivo
                               FROM            salidas INNER JOIN
                                                         consumos ON salidas.consumoid = consumos.consumoid INNER JOIN
                                                         albaranes AS albaranes ON consumos.fabricacionid = albaranes.fabricacion INNER JOIN
                                                         motivo ON salidas.motivoid = motivo.motivoid INNER JOIN
                                                         localizacion ON salidas.localizacionid = localizacion.localizacionid
                               WHERE        (albaranes.momento >= ?) AND (albaranes.momento < ?)) AS teoricosreales LEFT OUTER JOIN
                         materialtipo INNER JOIN
                         material ON materialtipo.materialtipoid = material.materialtipoid ON teoricosreales.materialid = material.materialid ON unidad.unidadid = teoricosreales.unidad ON 
                         albaranes.fabricacion = teoricosreales.fabricacionid LEFT OUTER JOIN
                             (SELECT DISTINCT fabricacion, material, humedad, localizacion
                               FROM            fabricacionhumedad) AS humedades ON teoricosreales.localizacionid = humedades.localizacion AND teoricosreales.fabricacionid = humedades.fabricacion AND 
                         teoricosreales.materialid = humedades.material LEFT OUTER JOIN
                         formulas ON albaranes.formula = formulas.formulaid LEFT OUTER JOIN
                         obras LEFT OUTER JOIN
                         clientes ON obras.cliente = clientes.clienteid ON albaranes.obra = obras.obraid
WHERE        (albaranes.momento >= ?) AND (albaranes.momento < ?) 
ORDER BY albaranes.serie, albaranes.numeroalbaran, material.materialid, teoricosreales.ciclo