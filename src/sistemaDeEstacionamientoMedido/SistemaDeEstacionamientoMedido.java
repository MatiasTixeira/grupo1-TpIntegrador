package sistemaDeEstacionamientoMedido;

import java.time.LocalTime;

import sectorDeCompras.ISectorDeCompras;
import sectorDeEstacionamiento.ISectorDeEstacionamiento;
import sectorDeInfracciones.ISectorDeInfracciones;
import sectorDeSaldos.ISectorSaldo;

import sectorDeZonas.ISectorZonas;
import suscripcion.IGestorDeSuscripcion;

public class SistemaDeEstacionamientoMedido {
	
		LocalTime horaInicio; 
		LocalTime horaFin; 
		Double precioPorHora;
		ISectorDeEstacionamiento sectorEstacionamiento; 
		ISectorSaldo sectorSsaldol;
		ISectorZonas sectorZona; 
		ISectorDeCompras sectorCompra;
		ISectorDeInfracciones sectorInfraccion;
		IGestorDeSuscripcion sectorSuscripcion;
		

		public SistemaDeEstacionamientoMedido(LocalTime horaInicio, LocalTime horaFin, Double precioPorHora,
				ISectorDeEstacionamiento sectorEstacionamiento, ISectorSaldo sectorSsaldol, ISectorZonas sectorZona,
				ISectorDeCompras sectorCompra, ISectorDeInfracciones sectorInfraccion,
				IGestorDeSuscripcion sectorSuscripcion) {
			super();
			this.horaInicio = horaInicio;
			this.horaFin = horaFin;
			this.precioPorHora = precioPorHora;
			this.sectorEstacionamiento = sectorEstacionamiento;
			this.sectorSsaldol = sectorSsaldol;
			this.sectorZona = sectorZona;
			this.sectorCompra = sectorCompra;
			this.sectorInfraccion = sectorInfraccion;
			this.sectorSuscripcion = sectorSuscripcion;
		}
		
		public LocalTime getHoraInicio() {
			return horaInicio;
		}
		public void setHoraInicio(LocalTime horaInicio) {
			this.horaInicio = horaInicio;
		}
		public LocalTime getHoraFin() {
			return horaFin;
		}
		public void setHoraFin(LocalTime horaFin) {
			this.horaFin = horaFin;
		}
		public Double getPrecioPorHora() {
			return precioPorHora;
		}
		public void setPrecioPorHora(Double precioPorHora) {
			this.precioPorHora = precioPorHora;
		}
		public ISectorDeEstacionamiento getSectorEstacionamiento() {
			return sectorEstacionamiento;
		}
		public void setSectorEstacionamiento(ISectorDeEstacionamiento sectorEstacionamiento) {
			this.sectorEstacionamiento = sectorEstacionamiento;
		}
		public ISectorSaldo getSectorSsaldol() {
			return sectorSsaldol;
		}
		public void setSectorSsaldol(ISectorSaldo sectorSsaldol) {
			this.sectorSsaldol = sectorSsaldol;
		}
		public ISectorZonas getSectorZona() {
			return sectorZona;
		}
		public void setSectorZona(ISectorZonas sectorZona) {
			this.sectorZona = sectorZona;
		}
		public ISectorDeCompras getSectorCompra() {
			return sectorCompra;
		}
		public void setSectorCompra(ISectorDeCompras sectorCompra) {
			this.sectorCompra = sectorCompra;
		}
		public ISectorDeInfracciones getSectorInfraccion() {
			return sectorInfraccion;
		}
		public void setSectorInfraccion(ISectorDeInfracciones sectorInfraccion) {
			this.sectorInfraccion = sectorInfraccion;
		}
		public IGestorDeSuscripcion getSectorSuscripcion() {
			return sectorSuscripcion;
		}
		public void setSectorSuscripcion(IGestorDeSuscripcion sectorSuscripcion) {
			this.sectorSuscripcion = sectorSuscripcion;
		}
}
